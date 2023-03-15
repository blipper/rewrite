/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.java.trait.expr;

import lombok.AllArgsConstructor;
import org.openrewrite.Cursor;
import org.openrewrite.Tree;
import org.openrewrite.java.trait.internal.MaybeParenthesesPair;
import org.openrewrite.java.trait.util.TraitErrors;
import org.openrewrite.java.trait.util.Validation;
import org.openrewrite.java.tree.Expression;
import org.openrewrite.java.tree.J;

import java.util.Iterator;
import java.util.function.Predicate;

public interface VarAccess extends Expr {

    String getName();


    boolean hasQualifier();

    /**
     * True if this access refers to a local variable or a field of
     * the receiver of the enclosing method or constructor.
     */
    boolean isLocal();

    /**
     * Holds if this variable access is an l-value.
     * </p>
     * An l-value is a write access to a variable, which occurs as the destination of an assignment.
     */
    boolean isLValue();

    /**
     * Holds if this variable access is an r-value.
     * </p>
     * An r-value is a read access to a variable.
     * In other words, it is a variable access that does _not_ occur as the destination of
     * a simple assignment, but it may occur as the destination of a compound assignment
     * or a unary assignment.
     */
    boolean isRValue();

    static Validation<TraitErrors, VarAccess> viewOf(Cursor cursor) {
        if (cursor.getValue() instanceof J.Identifier) {
            J.Identifier ident = cursor.getValue();
            return VarAccessBase.viewOf(cursor, ident);
        }
        return TraitErrors.invalidTraitCreationType(VarAccess.class, cursor, J.Identifier.class);
    }
}

@AllArgsConstructor
class VarAccessBase implements VarAccess {
    private final Cursor cursor;
    private final J.Identifier identifier;

    @Override
    public String getName() {
        return identifier.getSimpleName();
    }

    @Override
    public boolean hasQualifier() {
        return false;
    }

    @Override
    public boolean isLocal() {
        return true;
    }

    @Override
    public boolean isLValue() {
        MaybeParenthesesPair pair = MaybeParenthesesPair.from(cursor);
        if (pair.getParent() instanceof J.Assignment) {
            J.Assignment assignment = (J.Assignment) pair.getParent();
            return assignment.getVariable() == pair.getTree();
        }
        if (pair.getParent() instanceof J.Unary) {
            J.Unary unary = (J.Unary) pair.getParent();
            return unary.getExpression() == pair.getTree();
        }
        return false;
    }

    @Override
    public boolean isRValue() {
        MaybeParenthesesPair pair = MaybeParenthesesPair.from(cursor);
        if (pair.getParent() instanceof J.Assignment) {
            J.Assignment assignment = (J.Assignment) pair.getParent();
            return assignment.getVariable() != pair.getTree();
        }
        return true ;
    }

    static Validation<TraitErrors, VarAccess> viewOf(Cursor cursor, J.Identifier ident) {
        assert cursor.getValue() == ident;

        Cursor parent = cursor.getParentTreeCursor();
        // If the identifier is a new class name, those are not variable accesses.
        if (checkType(parent, J.NewClass.class, parentNewClass -> parentNewClass.getClazz() == ident)) {
            return TraitErrors.invalidTraitCreationError("J.Identifier within a new class statement is not a variable access");
        }
        if (checkType(parent, J.MethodInvocation.class, parentMethodInvocation -> parentMethodInvocation.getName() == ident)) {
            return TraitErrors.invalidTraitCreationError("J.Identifier within a method invocation name is not a variable access");
        }
        if (checkType(parent, J.MethodDeclaration.class, parentMethodDeclaration -> parentMethodDeclaration.getName() == ident)) {
            return TraitErrors.invalidTraitCreationError("J.Identifier within a method declaration name is not a variable access");
        }

        // Special case for type casts, where the identifier is the class part of the type cast.
        if (checkType(parent, J.ControlParentheses.class, parentControlParentheses ->
                parentControlParentheses.getTree() == ident &&
                        checkType(parent.getParentTreeCursor(), J.TypeCast.class, parentParentTypeCast -> parentParentTypeCast.getClazz() == parentControlParentheses))) {
            return TraitErrors.invalidTraitCreationError("J.Identifier within a type cast class part is not a variable access");
        }
        // Special case for annotations, where the left side of the assignment is the annotation field name, and not a variable access.
        if (checkType(parent, J.Assignment.class, parentAssignment ->
                parentAssignment.getVariable() == ident &&
                        checkType(parent.getParentTreeCursor(), J.Annotation.class, parentParentAnnotation -> parentParentAnnotation.getArguments() != null && parentParentAnnotation.getArguments().contains(parentAssignment)))) {
            return TraitErrors.invalidTraitCreationError("J.Identifier within an annotation argument's argument label is not a variable access");
        }

        if (checkType(parent, J.MethodInvocation.class, parentMethodInvocation -> parentMethodInvocation.getSelect() == ident || parentMethodInvocation.getArguments().contains(ident)) ||
                checkType(parent, J.NewClass.class, parentNewClass -> parentNewClass.getEnclosing() == ident || parentNewClass.getArguments().contains(ident)) ||
                checkType(parent, J.Parentheses.class, parentParentheses -> parentParentheses.getTree() == ident) ||
                checkType(parent, J.Unary.class, parentUnary -> parentUnary.getExpression() == ident) ||
                checkType(parent, J.Binary.class, parentBinary -> parentBinary.getLeft() == ident || parentBinary.getRight() == ident) ||
                checkType(parent, J.VariableDeclarations.NamedVariable.class, parentNamedVariable -> parentNamedVariable.getInitializer() == ident) ||
                checkType(parent, J.Assignment.class, parentAssignment -> parentAssignment.getVariable() == ident || parentAssignment.getAssignment() == ident) ||
                checkType(parent, J.TypeCast.class, parentTypeCast -> parentTypeCast.getExpression() == ident) ||
                checkType(parent, J.ControlParentheses.class, parentControlParentheses -> parentControlParentheses.getTree() == ident) ||
                checkType(parent, J.ForEachLoop.Control.class, parentForEachLoopControl -> parentForEachLoopControl.getIterable() == ident) ||
                checkType(parent, J.ForLoop.Control.class, parentForLoopControl -> parentForLoopControl.getCondition() == ident) ||
                checkType(parent, J.NewArray.class, parentNewArray -> parentNewArray.getInitializer() != null && parentNewArray.getInitializer().contains(ident)) ||
                checkType(parent, J.ArrayDimension.class, parentArrayDimension -> parentArrayDimension.getIndex() == ident) ||
                checkType(parent, J.ArrayAccess.class, parentArrayAccess -> parentArrayAccess.getIndexed() == ident) ||
                checkType(parent, J.Ternary.class, parentTernary -> parentTernary.getCondition() == ident ||
                        parentTernary.getTruePart() == ident ||
                        parentTernary.getFalsePart() == ident) ||
                checkType(parent, J.Annotation.class, parentAnnotation -> parentAnnotation.getArguments() != null && parentAnnotation.getArguments().contains(ident))) {
            return Validation.success(new VarAccessBase(cursor, ident));
        }

        // Check if the ident appears within an import statement. Those are not variable accesses.
        if (cursor.getPathAsStream(J.Import.class::isInstance).findAny().isPresent()) {
            return TraitErrors.invalidTraitCreationError("J.Identifier within an import statement is not a variable access");
        }
        //Check if the ident appears within a package statement. Those are not variable accesses.
        if (cursor.getPathAsStream(J.Package.class::isInstance).findAny().isPresent()) {
            return TraitErrors.invalidTraitCreationError("J.Identifier within a package statement is not a variable access");
        }
        // Catch all. Useful point for setting a breakpoint when debugging.
        return TraitErrors.invalidTraitCreationError("J.Identifier is not a variable access");
    }

    static <T> boolean checkType(Cursor parent, Class<T> tClass, Predicate<T> predicate) {
        Object tree = parent.getValue();
        if (tClass.isInstance(tree)) {
            return predicate.test(tClass.cast(tree));
        }
        return false;
    }
}


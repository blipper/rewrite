package org.openrewrite.java.trait.expr;

import org.junit.jupiter.api.Test;
import org.openrewrite.ExecutionContext;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;
import org.openrewrite.marker.SearchResult;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;
import static org.openrewrite.test.RewriteTest.toRecipe;

@SuppressWarnings("ALL")
public class VarAccessTest implements RewriteTest {
    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(toRecipe(() -> new JavaIsoVisitor<>() {
            @Override
            public J preVisit(J tree, ExecutionContext executionContext) {
                return VarAccess.viewOf(getCursor())
                  .map(var -> SearchResult.foundMerging(tree, var.getName() + " local:" + var.isLocal() + " l:" + var.isLValue() + " r:" + var.isRValue() + " q:" + var.hasQualifier()))
                  .orSuccess(tree);
            }
        })).cycles(1).expectedCyclesThatMakeChanges(1);
    }

    @Test
    void correctlyLabelsLocalVariables() {
        rewriteRun(
          java(
            """
              class Test {
                  void test(int a) {
                      int i = a;
                      i = 1;
                  }
              }
              """,
            """
              class Test {
                  void test(int a) {
                      int i = /*~~(a local:true l:false r:true q:false)~~>*/a;
                      /*~~(i local:true l:true r:false q:false)~~>*/i = 1;
                  }
              }
              """
          )
        );
    }

    @Test
    void correctlyLabelsLocalVariablesParenthesesWrapped() {
        rewriteRun(
          java(
            """
              class Test {
                  void test(int a) {
                      int i = (a);
                      (i) = 1;
                  }
              }
              """,
            """
              class Test {
                  void test(int a) {
                      int i = (/*~~(a local:true l:false r:true q:false)~~>*/a);
                      (/*~~(i local:true l:true r:false q:false)~~>*/i) = 1;
                  }
              }
              """
          )
        );
    }
}

/*
 * Copyright 2022 the original author or authors.
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
package org.openrewrite.cobol;

import org.openrewrite.cobol.tree.Cobol;

public class CobolIsoVisitor<P> extends CobolVisitor<P> {

    @Override
    public Cobol.CompilationUnit visitCompilationUnit(Cobol.CompilationUnit compilationUnit, P p) {
        return (Cobol.CompilationUnit) super.visitCompilationUnit(compilationUnit, p);
    }

    @Override
    public Cobol.Display visitDisplay(Cobol.Display display, P p) {
        return (Cobol.Display) super.visitDisplay(display, p);
    }

    @Override
    public Cobol.Identifier visitIdentifier(Cobol.Identifier identifier, P p) {
        return (Cobol.Identifier) super.visitIdentifier(identifier, p);
    }

    @Override
    public Cobol.Literal visitLiteral(Cobol.Literal literal, P p) {
        return (Cobol.Literal) super.visitLiteral(literal, p);
    }

    @Override
    public Cobol.IdentificationDivision visitIdentificationDivision(Cobol.IdentificationDivision identificationDivision, P p) {
        return (Cobol.IdentificationDivision) super.visitIdentificationDivision(identificationDivision, p);
    }

    @Override
    public Cobol.ProcedureDivision visitProcedureDivision(Cobol.ProcedureDivision procedureDivision, P p) {
        return (Cobol.ProcedureDivision) super.visitProcedureDivision(procedureDivision, p);
    }

    @Override
    public Cobol.ProcedureDivisionBody visitProcedureDivisionBody(Cobol.ProcedureDivisionBody procedureDivisionBody, P p) {
        return (Cobol.ProcedureDivisionBody) super.visitProcedureDivisionBody(procedureDivisionBody, p);
    }

    @Override
    public Cobol.Paragraphs visitParagraphs(Cobol.Paragraphs paragraphs, P p) {
        return (Cobol.Paragraphs) super.visitParagraphs(paragraphs, p);
    }

    @Override
    public Cobol.Sentence visitSentence(Cobol.Sentence sentence, P p) {
        return (Cobol.Sentence) super.visitSentence(sentence, p);
    }

    @Override
    public Cobol.ProgramIdParagraph visitProgramIdParagraph(Cobol.ProgramIdParagraph programIdParagraph, P p) {
        return (Cobol.ProgramIdParagraph) super.visitProgramIdParagraph(programIdParagraph, p);
    }

    @Override
    public Cobol.ProgramUnit visitProgramUnit(Cobol.ProgramUnit programUnit, P p) {
        return (Cobol.ProgramUnit) super.visitProgramUnit(programUnit, p);
    }

    @Override
    public Cobol.Stop visitStop(Cobol.Stop stop, P p) {
        return (Cobol.Stop) super.visitStop(stop, p);
    }

    @Override
    public Cobol.DataDivision visitDataDivision(Cobol.DataDivision dataDivision, P p) {
        return (Cobol.DataDivision) super.visitDataDivision(dataDivision, p);
    }

    @Override
    public Cobol.DataDescriptionEntry visitDataDescriptionEntry(Cobol.DataDescriptionEntry dataDescriptionEntry, P p) {
        return (Cobol.DataDescriptionEntry) super.visitDataDescriptionEntry(dataDescriptionEntry, p);
    }

    @Override
    public Cobol.DataPictureClause visitDataPictureClause(Cobol.DataPictureClause dataPictureClause, P p) {
        return (Cobol.DataPictureClause) super.visitDataPictureClause(dataPictureClause, p);
    }

    @Override
    public Cobol.Picture visitPicture(Cobol.Picture picture, P p) {
        return (Cobol.Picture) super.visitPicture(picture, p);
    }

    @Override
    public Cobol.WorkingStorageSection visitWorkingStorageSection(Cobol.WorkingStorageSection workingStorageSection, P p) {
        return (Cobol.WorkingStorageSection) super.visitWorkingStorageSection(workingStorageSection, p);
    }

    @Override
    public Cobol.EndProgram visitEndProgram(Cobol.EndProgram endProgram, P p) {
        return (Cobol.EndProgram) super.visitEndProgram(endProgram, p);
    }

    @Override
    public Cobol.EnvironmentDivision visitEnvironmentDivision(Cobol.EnvironmentDivision environmentDivision, P p) {
        return (Cobol.EnvironmentDivision) super.visitEnvironmentDivision(environmentDivision, p);
    }

    @Override
    public Cobol.Set visitSet(Cobol.Set set, P p) {
        return (Cobol.Set) super.visitSet(set, p);
    }

    @Override
    public Cobol.SetTo visitSetTo(Cobol.SetTo setTo, P p) {
        return (Cobol.SetTo) super.visitSetTo(setTo, p);
    }

    @Override
    public Cobol.SetUpDown visitSetUpDown(Cobol.SetUpDown setUpDown, P p) {
        return (Cobol.SetUpDown) super.visitSetUpDown(setUpDown, p);
    }

    @Override
    public Cobol.Add visitAdd(Cobol.Add add, P p) {
        return (Cobol.Add) super.visitAdd(add, p);
    }

    @Override
    public Cobol.AddTo visitAddTo(Cobol.AddTo addTo, P p) {
        return (Cobol.AddTo) super.visitAddTo(addTo, p);
    }

    @Override
    public Cobol.StatementPhrase visitStatementPhrase(Cobol.StatementPhrase statementPhrase, P p) {
        return (Cobol.StatementPhrase) super.visitStatementPhrase(statementPhrase, p);
    }

    @Override
    public Cobol.Roundable visitRoundable(Cobol.Roundable roundable, P p) {
        return (Cobol.Roundable) super.visitRoundable(roundable, p);
    }

    @Override
    public Cobol.ConfigurationSection visitConfigurationSection(Cobol.ConfigurationSection configurationSection, P p) {
        return (Cobol.ConfigurationSection) super.visitConfigurationSection(configurationSection, p);
    }

    @Override
    public Cobol.SourceComputer visitSourceComputer(Cobol.SourceComputer sourceComputer, P p) {
        return (Cobol.SourceComputer) super.visitSourceComputer(sourceComputer, p);
    }

    @Override
    public Cobol.SourceComputerDefinition visitSourceComputerDefinition(Cobol.SourceComputerDefinition sourceComputerDefinition, P p) {
        return (Cobol.SourceComputerDefinition) super.visitSourceComputerDefinition(sourceComputerDefinition, p);
    }

    @Override
    public Cobol.ObjectComputer visitObjectComputer(Cobol.ObjectComputer objectComputer, P p) {
        return (Cobol.ObjectComputer) super.visitObjectComputer(objectComputer, p);
    }

    @Override
    public Cobol.ObjectComputerDefinition visitObjectComputerDefinition(Cobol.ObjectComputerDefinition objectComputerDefinition, P p) {
        return (Cobol.ObjectComputerDefinition) super.visitObjectComputerDefinition(objectComputerDefinition, p);
    }

    @Override
    public Cobol.ValuedObjectComputerClause visitValuedObjectComputerClause(Cobol.ValuedObjectComputerClause valuedObjectComputerClause, P p) {
        return (Cobol.ValuedObjectComputerClause) super.visitValuedObjectComputerClause(valuedObjectComputerClause, p);
    }

    @Override
    public Cobol.CollatingSequenceClause visitCollatingSequenceClause(Cobol.CollatingSequenceClause collatingSequenceClause, P p) {
        return (Cobol.CollatingSequenceClause) super.visitCollatingSequenceClause(collatingSequenceClause, p);
    }

    @Override
    public Cobol.CollatingSequenceAlphabet visitCollatingSequenceAlphabet(Cobol.CollatingSequenceAlphabet collatingSequenceAlphabet, P p) {
        return (Cobol.CollatingSequenceAlphabet) super.visitCollatingSequenceAlphabet(collatingSequenceAlphabet, p);
    }

    @Override
    public Cobol.AlphabetClause visitAlphabetClause(Cobol.AlphabetClause alphabetClause, P p) {
        return (Cobol.AlphabetClause) super.visitAlphabetClause(alphabetClause, p);
    }

    @Override
    public Cobol.AlphabetLiteral visitAlphabetLiteral(Cobol.AlphabetLiteral alphabetLiteral, P p) {
        return (Cobol.AlphabetLiteral) super.visitAlphabetLiteral(alphabetLiteral, p);
    }

    @Override
    public Cobol.AlphabetThrough visitAlphabetThrough(Cobol.AlphabetThrough alphabetThrough, P p) {
        return (Cobol.AlphabetThrough) super.visitAlphabetThrough(alphabetThrough, p);
    }

    @Override
    public Cobol.AlphabetAlso visitAlphabetAlso(Cobol.AlphabetAlso alphabetAlso, P p) {
        return (Cobol.AlphabetAlso) super.visitAlphabetAlso(alphabetAlso, p);
    }

    @Override
    public Cobol.SpecialNames visitSpecialNames(Cobol.SpecialNames specialNames, P p) {
        return (Cobol.SpecialNames) super.visitSpecialNames(specialNames, p);
    }

    @Override
    public Cobol.ChannelClause visitChannelClause(Cobol.ChannelClause channelClause, P p) {
        return (Cobol.ChannelClause) super.visitChannelClause(channelClause, p);
    }

    @Override
    public Cobol.CurrencyClause visitCurrencyClause(Cobol.CurrencyClause currencyClause, P p) {
        return (Cobol.CurrencyClause) super.visitCurrencyClause(currencyClause, p);
    }

    @Override
    public Cobol.DecimalPointClause visitDecimalPointClause(Cobol.DecimalPointClause decimalPointClause, P p) {
        return (Cobol.DecimalPointClause) super.visitDecimalPointClause(decimalPointClause, p);
    }

    @Override
    public Cobol.DefaultComputationalSignClause visitDefaultComputationalSignClause(Cobol.DefaultComputationalSignClause defaultComputationalSignClause, P p) {
        return (Cobol.DefaultComputationalSignClause) super.visitDefaultComputationalSignClause(defaultComputationalSignClause, p);
    }

    @Override
    public Cobol.DefaultDisplaySignClause visitDefaultDisplaySignClause(Cobol.DefaultDisplaySignClause defaultDisplaySignClause, P p) {
        return (Cobol.DefaultDisplaySignClause) super.visitDefaultDisplaySignClause(defaultDisplaySignClause, p);
    }

    @Override
    public Cobol.ClassClause visitClassClause(Cobol.ClassClause classClause, P p) {
        return (Cobol.ClassClause) super.visitClassClause(classClause, p);
    }

    @Override
    public Cobol.ClassClauseThrough visitClassClauseThrough(Cobol.ClassClauseThrough classClauseThrough, P p) {
        return (Cobol.ClassClauseThrough) super.visitClassClauseThrough(classClauseThrough, p);
    }

    @Override
    public Cobol.OdtClause visitOdtClause(Cobol.OdtClause odtClause, P p) {
        return (Cobol.OdtClause) super.visitOdtClause(odtClause, p);
    }

    @Override
    public Cobol.ReserveNetworkClause visitReserveNetworkClause(Cobol.ReserveNetworkClause reserveNetworkClause, P p) {
        return (Cobol.ReserveNetworkClause) super.visitReserveNetworkClause(reserveNetworkClause, p);
    }

    @Override
    public Cobol.SymbolicCharacter visitSymbolicCharacter(Cobol.SymbolicCharacter symbolicCharacter, P p) {
        return (Cobol.SymbolicCharacter) super.visitSymbolicCharacter(symbolicCharacter, p);
    }

    @Override
    public Cobol.SymbolicCharactersClause visitSymbolicCharactersClause(Cobol.SymbolicCharactersClause symbolicCharactersClause, P p) {
        return (Cobol.SymbolicCharactersClause) super.visitSymbolicCharactersClause(symbolicCharactersClause, p);
    }

    @Override
    public Cobol.FileSection visitFileSection(Cobol.FileSection fileSection, P p) {
        return (Cobol.FileSection) super.visitFileSection(fileSection, p);
    }

    @Override
    public Cobol.FileDescriptionEntry visitFileDescriptionEntry(Cobol.FileDescriptionEntry fileDescriptionEntry, P p) {
        return (Cobol.FileDescriptionEntry) super.visitFileDescriptionEntry(fileDescriptionEntry, p);
    }

    @Override
    public Cobol.LinkageSection visitLinkageSection(Cobol.LinkageSection linkageSection, P p) {
        return (Cobol.LinkageSection) super.visitLinkageSection(linkageSection, p);
    }

    @Override
    public Cobol.LocalStorageSection visitLocalStorageSection(Cobol.LocalStorageSection localStorageSection, P p) {
        return (Cobol.LocalStorageSection) super.visitLocalStorageSection(localStorageSection, p);
    }

    @Override
    public Cobol.DataBaseSection visitDataBaseSection(Cobol.DataBaseSection dataBaseSection, P p) {
        return (Cobol.DataBaseSection) super.visitDataBaseSection(dataBaseSection, p);
    }

    @Override
    public Cobol.DataBaseSectionEntry visitDataBaseSectionEntry(Cobol.DataBaseSectionEntry dataBaseSectionEntry, P p) {
        return (Cobol.DataBaseSectionEntry) super.visitDataBaseSectionEntry(dataBaseSectionEntry, p);
    }
}
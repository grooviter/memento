package io.grooviter.memento.impl.ast

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class AggregateTransformation extends AbstractASTTransformation {
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        ClassNode classNode = (ClassNode) astNodes[1]

        classNode.implementsInterface(ClassHelper.make(Number))
    }
}

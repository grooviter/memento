ruleset {
  description 'Memento ruleset'

  ruleset('rulesets/comments.xml') {  
    'ClassJavadoc' {
      enabled = false
    }
  }
  ruleset('rulesets/basic.xml')
  ruleset('rulesets/exceptions.xml')
  ruleset('rulesets/imports.xml')
  ruleset('rulesets/unused.xml')
  ruleset('rulesets/dry.xml') {
    'DuplicateStringLiteral' {
      doNotApplyToClassNames = '*Spec'
    }
    'DuplicateMapLiteral' {
      doNotApplyToClassNames = '*Spec'
    }
    'DuplicateNumberLiteral' {
      doNotApplyToClassNames = '*Spec'
    }
    'DuplicateListLiteral' {
      doNotApplyToClassNames = '*Spec'
    }
  }
  ruleset('rulesets/formatting.xml') {
    'SpaceAroundMapEntryColon' {
      enabled = false
    }
    'FileEndsWithoutNewline' {
      enabled = false
    }
    'LineLength' {
      doNotApplyToFileNames = '*Spec.*'
    }
    'MissingBlankLineAfterPackage' {
      enabled = false
    }
    // Error with Groovy 3.0.7 and Codenarc 2.0.0
    'ClassStartsWithBlankLine' {
      enabled = false
    }
  }
  ruleset('rulesets/naming.xml') {
    'MethodName' {
      doNotApplyToClassNames = '*Spec'
    }
    'FactoryMethodName' {
      enabled = false
    }
    'VariableName' {
      finalRegex = 	/[a-z][a-zA-Z0-9]*/
    }
  }
  ruleset('rulesets/convention.xml') {
    'CompileStatic' {
      // TODO see Enterprise Groovy Plugin
      enabled = false
    }
    'NoDef' {
      enabled = false
    }
  }
}

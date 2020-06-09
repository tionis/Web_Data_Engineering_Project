# Branching Model
This project is required to follow the [Gitflow Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow),
as such the master branch is only used for releases and is protected.  
Development happens mainly on the develop and specific feature branches.  
Hotfix branches are part of the spec, but will not be used as this code never moves into production.  
Nevertheless release tags shall be used when appropiate.

# Commit Template
Commits shall be written after conventions from [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) with following types:
 - fix for bug fixes
 - feat for added features
 - docs for documentation
 - chore for housekeeping
 - style for style improvements
 - refactor for code refactors
 - perf for perfomance improvements 
 - test for testing commits
 - build for modification of the build system
 - org for commits needed for organization of the project
 
This means commits have to follow this syntax:
```
<type>[optional scope in brackets]: <description>

[optional body]

[optional footer]
```

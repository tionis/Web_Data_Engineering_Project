# Branching Model
This project is required to follow the [Gitflow Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow),
as such the master branch is only used for releases and is protected.  
Development happens mainly on the develop and specific feature branches.  
Hotfix branches are part of the spec, but will not be used as this code never moves into production.  
Nevertheless, release tags shall be used when appropriate.


# Naming Conventions
## Branch Template
Branches should(next to the master and develop branch) follow this naming convention: type/descriptor  

Following types can be used:
 - feat: Feature adding or expanding
 - fix: Bug fix or experiment
 - wip: Works in progress
 - junk: Throwaway branch created to experiment
 
The descriptor can be any valid git branch name, as long as it is short and descriptive.

## Commit Template
Commits shall be written after conventions from [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) with following types:
 - fix: bug fixes
 - feat: added features
 - docs: documentation
 - wip: work in progress (e.g. commits that are needed for debugging and collaboration)
 - chore: housekeeping
 - style: style improvements
 - refactor: code refactors
 - perf: performance improvements 
 - test: testing commits
 - build: modification of the build system
 - org: commits needed for organization of the project
 
This means commits have to follow this syntax:
```
<type>[optional scope in brackets]: <description>

[optional body]

[optional footer]
```

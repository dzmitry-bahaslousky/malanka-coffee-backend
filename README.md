# Malanka Coffee

## Run GitHub Actions Locally

To run a GitHub Action locally, you can use the tool called act. 
This tool allows you to execute GitHub Actions on your local machine, simulating the GitHub environment.

1. Install [act](https://nektosact.com/installation/index.html) on your local machine
2. Check available workflows 
```shell 
act -l 
``` 

To run CI `.github/workflows/ci.yaml` use
```shell
act -W '.github/workflows/ci.yaml'
```

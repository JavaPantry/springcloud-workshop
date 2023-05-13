# Dummy Quart web API for KB

## Copied from Dummy flask web API for KB
- created Flask Web base app in `c:\PythonWs\WebFileExplorer\flask-service\` (base code copied from c:\UbuntuWS\DockerSuiteTest\flask-service\ - small docker for python and vue)
- analyzed and copied requirements from  `C:\PythonWs\BlueprintProductionReadyWebAppPacktBook\tozo\backend\pyproject.toml`

```
quart>=0.18.3
quart-auth>=0.8.0
quart-schema>=0.14.3
quart-db>=0.4.1
bcrypt>=4.0.1
```

- confirm what installed
  - looks like vs code installed everything from scoop\python 11

```

(.venv) C:\PythonWs\WebFileExplorer\quart-service>pip list
Package           Version
----------------- -------
aiofiles          23.1.0 
asyncpg           0.27.0 
bcrypt            4.0.1  
blinker           1.6    
buildpg           0.4    
click             8.1.3  
colorama          0.4.6  
h11               0.14.0
h2                4.1.0
hpack             4.0.0
hypercorn         0.14.3
hyperframe        6.0.1
itsdangerous      2.1.2
Jinja2            3.1.2
MarkupSafe        2.1.2
pip               23.0.1
priority          2.0.0
pydantic          1.10.7
pyhumps           3.8.0
quart             0.18.3
quart-auth        0.8.0
quart-db          0.4.1
quart-schema      0.15.0
setuptools        65.5.0
toml              0.10.2
typing_extensions 4.5.0
Werkzeug          2.2.3
wsproto           1.2.0
```

## Setup Environment with VsCode
- Ctrl+P, select Create Python Virtual Environment
  - select scoop\python 11
  - install requirements.txt (from VsCode)
- C:\Users\Alexei\scoop\apps\python\current\python.exe -m venv .venv

## switch from Flask to Quart
- success test http://localhost:5000/
- success test http://localhost:5000/readFolder?folder=myapi
- success test http://localhost:5000/hello/Alex
- add `if __name__ == "__main__": app.run(debug=True, host="127.0.0.1", port=5000)`
  - debug session still interrupted on code changes

## move test routes to control.py
- fix .gitignore
- 

## Old notes Delete old flask imagesDelete old flask images
docker image ls
docker image rm sbsuite-quart

docker image build -t sbsuite-quart .
docker image ls
docker container run -d -p 5000:5000 sbsuite-quart
docker container ls -a
docker rm <containerId>
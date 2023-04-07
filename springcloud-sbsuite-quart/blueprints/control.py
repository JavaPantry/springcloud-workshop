from datetime import datetime

from quart import Blueprint, ResponseReturnValue, request
blueprint = Blueprint("control", __name__)


# route to read folder (passed as Query Parameters) contents
# test: http://localhost:5000/readFolder?folder=api
@blueprint.get("/readFolder")
def readFolder():
    folder = request.args.get('folder')
    response = F"GET request path \"/readFolder\" folder: {folder}"
    print(folder)
    return response

@blueprint.get("/folder/<path:folder>")
def folder(folder):
    print(folder)
    return folder

@blueprint.get("/hello/<name>")
def hello_there(name):
    now = datetime.now()
    formatted_now = now.strftime("%A, %d %B, %Y at %X")
    content = "Hello there, " + name + "! It's " + formatted_now
    return content

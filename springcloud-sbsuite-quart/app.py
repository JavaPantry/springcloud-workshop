from quart import Quart
from quart import request
# from quart.cors import cors
# from quart_cors import cors
# import re
import py_eureka_client.eureka_client as eureka_client

from utils.FileSysExplorer import FileSysExplorer

from blueprints.control import blueprint as control_blueprint

rest_port = 5000
eureka_client.init(eureka_server="http://localhost:8761/eureka",
                           app_name="data-aggregation-service",
                           instance_port=rest_port)



app = Quart(__name__)
#app = cors(app)


@app.route("/")
def home():
    return "Hello, Quart!"

app.register_blueprint(control_blueprint)

# start server with: python app.py
# open in browser - success test http://localhost:5000/
# open in browser - success test http://localhost:5000/readFolder?folder=myapi
# open in browser - success test http://localhost:5000/hello/Alex
if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=rest_port)
    # app.run(debug=True, host="127.0.0.1", port=rest_port)
# app.run()
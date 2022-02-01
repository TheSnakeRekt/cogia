import express from 'express';
import * as http from 'http';
import 'reflect-metadata'
import cors from 'cors';
require('dotenv').config();

let compression = require("compression");

class FrontServer {

    readonly runningMessage = `Server running at ${process.env.BASE_URL}`;

    public app!: express.Application;
    server!: http.Server; 
    port = 3000;


    constructor(){
        this.init();
        this.listen(this.port);
    }   

    private init(){
        this.app = express();
        this.server = http.createServer(this.app);

        this.app.use(express.json());
   
        this.app.use(cors());
        this.app.use(compression());
        this.app.use(express.static('./dist/cogia-front'))


        this.app.use(function(req, res, next) {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            next();
        });

    }

    private listen(port: number){
        this.server.listen(port, () => {
        
            console.log(this.runningMessage);
        });
    }
}

export default new FrontServer()
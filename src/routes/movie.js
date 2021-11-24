const express = require('express');
const { database } = require('../database.js');
const router = express.Router();

const pool = require('../database.js');

router.get('/',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    let MovieList = await pool.query('SELECT * FROM movie');
    res.json({
        status:"200",
        message:"Movies have been successfully retrieved",
        MovieList: MovieList
    });
});

router.get('/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    let movie = await pool.query('SELECT * FROM movie WHERE id=?',[id]);
    res.json({
        status:"200",
        message:"The movie has been successfully retrieved",
        movie: movie
    });
});

router.post('/create',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const dateNow = Date.now();
    const registered_date = new Date(dateNow);
    const updated_date = new Date(dateNow);
    const state = 1
    const {title,description,synopsis,rating,category} = req.body;
    const movie={title,description,synopsis,rating,registered_date,updated_date,state,category};
    await pool.query('INSERT INTO movie set ?', [movie]);
    res.json({
        status:"200",
        message:"Movie has been successfully registered",
        movie: movie
    });
});

router.post('/update/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const dateNow = Date.now();
    const updated_date = new Date(dateNow);
    const {title,description,synopsis,rating,category} = req.body;
    const {id} = req.params;
    const movie= {title,description,synopsis,rating,updated_date,category};

    await pool.query('UPDATE movie SET ? WHERE id = ?',[movie,id]);
    
    res.json({
        status:"200",
        message:"The movie has been successfully updated",
        movie: movie
    });
});

router.post('/delete/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    await pool.query('DELETE FROM movie WHERE id = ?',[id]);
    res.json({
        status:"200",
        message:"The movie has been successfully removed"
    });
});

module.exports = router;
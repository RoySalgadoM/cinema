const express = require('express');
const { database } = require('../database.js');
const router = express.Router();

const pool = require('../database.js');

router.get('/',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    let categoryList = await pool.query('SELECT * FROM category');
    res.json({
        status:"200",
        message:"Categories have been successfully retrieved",
        categoryList: categoryList
    });
});

router.get('/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    let category = await pool.query('SELECT * FROM category WHERE id=?',[id]);
    res.json({
        status:"200",
        message:"The category has been successfully retrieved",
        category: category
    });
});

router.post('/create',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {name} = req.body;
    const category={name};
    await pool.query('INSERT INTO category set ?', [category]);
    res.json({
        status:"200",
        message:"Category has been successfully registered",
        category: category
    });
});

router.post('/update/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    const {name} = req.body;
    const category= {name};

    await pool.query('UPDATE category SET ? WHERE id = ?',[category,id]);
    
    res.json({
        status:"200",
        message:"The category has been successfully updated",
        category: category
    });
});

router.post('/delete/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    await pool.query('DELETE FROM category WHERE id = ?',[id]);
    res.json({
        status:"200",
        message:"The category has been successfully removed"
    });
});

module.exports = router;
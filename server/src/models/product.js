const { Schema, model } = require('mongoose');

const Product = new Schema({
    name: { type: String, required: true },
    description: String,
    type: String,
    price: { type: Number, required: true },
    status: String,
    image: { type: String, required: true },
});

module.exports = model('Product', Product);

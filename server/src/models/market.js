const { Schema, model } = require('mongoose');

const Market = new Schema({
    product: { type: Schema.Types.ObjectId, ref: 'Product' },
    seller: { type: Schema.Types.ObjectId, ref: 'User' },
    buyer: { type: Schema.Types.ObjectId, ref: 'User' },
    status: { type: String, enum: ['censored', 'pending', 'sold', 'shipping'] },
});

module.exports = model('Market', Market);

const { Schema, model } = require('mongoose');
const bcrypt = require('bcryptjs');

const User = new Schema({
    name: {
        type: String,
        required: true,
    },
    avatar: { type: String, default: '/images/avatardefault.webp' },
    email: {
        type: String,
        required: true,
        unique: true,
    },
    password: {
        type: String,
        required: true,
    },
    address: String,
    phone: String,
    role: {
        type: String,
        enum: ['admin', 'user'],
        default: 'user',
    },
});

User.pre('save', async function (next) {
    // 12 is salt length
    this.password = await bcrypt.hash(this.password, 12);
    next();
});

module.exports = model('User', User);

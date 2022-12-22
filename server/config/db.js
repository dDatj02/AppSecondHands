const mongoose = require('mongoose');

async function connect() {
    try {
        mongoose.set("strictQuery", false);
        mongoose.connect('mongodb://localhost:27017/mobile-final', {
            useNewUrlParser: true,
            useUnifiedTopology: true,
        });
        console.log('Connect db successfully');
    } catch (error) {
        console.log('Fail to connect db');
    }
}

module.exports = { connect };

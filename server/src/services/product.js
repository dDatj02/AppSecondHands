const { productModel } = require('../models');

const productService = {
    getOne: async (payloads, field='-__v') => {
        return await productModel.findOne(payloads, field).lean();
    },

    get: async (payloads, field='-__v') => {
        return await productModel.find(payloads, field).lean();
    },

    create: async (payloads) => {
        return await productModel.create(payloads);
    },

    update: async (conditions, payloads) => {
        return await productModel.findOneAndUpdate(conditions, payloads);
    },

    delete: async (conditions) => {
        return await productModel.findOneAndDelete(conditions);
    },
};

module.exports = productService;

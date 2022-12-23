const { productModel } = require('../models');

const productService = {
    getOne: async (query, options) => {
        field = options.field || '-__v';
        populate = options.populate || '';
        return await productModel.findOne(query, field).populate(populate).lean();
    },

    get: async (query, options) => {
        field = options.field || '-__v';
        populate = options.populate || '';
        return await productModel.find(query, field).populate(populate).lean();
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

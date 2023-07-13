import { defineStore } from 'pinia'

import Product from "@/models/Product";
import Shop from '@/models/Shop';

export const useShopStore = defineStore('shop', {
    state: () => {
        return {
            count: 3,
            currentShop: null as Shop | null, //TODO: reset on every new visit to the page
            shops: [] as Shop[],
            products: [] as Product[]
        }
    },
    actions: {
        setCurrentShop: function (shop: Shop) {
            this.currentShop = shop;
        },
        fetchProducts: async function () {
            try {
                console.log('entry fetchProducts()');
                const {data} = await useFetch('http://localhost:3099/products');
                console.log('fetchProducts() - data: ', data);
                this.products = data.value as Product[];
            } catch (error) {
                //showTooltip(error) - let the form component display the error
                return error
            }
        },
        fetchShops: async function () {
            try {
                console.log('entry fetchShops()');
                const {data} = await useFetch('http://localhost:3099/shops');
                console.log('fetchShops() - data: ', data);
                this.shops = data.value as Shop[];
            } catch (error) {
                //showTooltip(error) - let the form component display the error
                return error
            }
        },
    }
})
import { defineStore } from 'pinia'

// TODO: import { useFetch } from '@vueuse/core'

import Product from "@/models/Product";

export const useShopStore = defineStore('shop', {
    state: () => ({
        count: 3,
        shops: [
            {
                id: 1,
                name: 'Best Buy',
                address: 'Address 1',
                phone: 'Phone 1',
                email: 'Email 1',
                website: 'Website 1',
                description: 'Description 1',
                image: 'https://picsum.photos/200/300'
            },
            {
                id: 2,
                name: 'eCharge',
                address: 'Address 2',
                phone: 'Phone 2',
                email: 'Email 2',
                website: 'Website 2',
                description: 'Description 2',
                image: 'https://picsum.photos/200/300'
            },
            {
                id: 3,
                name: 'Canada Computers',
                address: 'Address 2',
                phone: 'Phone 2',
                email: 'Email 2',
                website: 'Website 2',
                description: 'Description 2',
                image: 'https://picsum.photos/200/300'
            }
            ],
        //products: <Product[]>[]
        products: [] as Product[]
    }),
    actions: {
        fetchProducts: async function () {
            try {
                console.log('entry fetchProducts()');
                //const {data} = await useFetch('https://fakestoreapi.com/products');
                //console.log('fetchProducts() - data: ', data);
                //this.products = data.value as Product[];
                const response = await fetch('https://fakestoreapi.com/products');
                const data = await response.json();
                this.products = data as Product[];
            } catch (error) {
                //showTooltip(error) - let the form component display the error
                return error
            }
        },
    }
})
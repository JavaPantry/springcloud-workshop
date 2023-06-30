import { defineStore } from 'pinia'

import Product  from "@/models/Product";

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
                const {data} = await useFetch('https://fakestoreapi.com/products');
                //TODO - fix this  typescript error `TS2352: Conversion of type 'Ref<unknown>' to type 'Product[]' may be a mistake because neither type sufficiently overlaps with the other.`
                // @ts-ignore
                this.products = data as Product[];
            } catch (error) {
                //showTooltip(error) - let the form component display the error
                return error
            }
        },
    }
})
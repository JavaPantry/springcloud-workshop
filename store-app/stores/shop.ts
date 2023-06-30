import { defineStore } from 'pinia'

export const useShopStore = defineStore('shop', {
    state: () => ({
        count: 1,
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
            }
            ]
    }),
})
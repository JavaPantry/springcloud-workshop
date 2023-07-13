<script setup lang="ts">
import { storeToRefs } from "pinia";
import { useShopStore } from '@/stores/shop'

const shopStore = useShopStore();

let { currentShop } = storeToRefs(shopStore)

</script>
<template>
        <v-app-bar
                color="primary"
                density="compact"
                >
                <template v-slot:prepend>
                    <v-app-bar-nav-icon></v-app-bar-nav-icon>
                </template>
    
                <v-app-bar-title>Store UI</v-app-bar-title>
    

                <!-- NOT available
                <v-icon icon="fa:fas fa-search"></v-icon>
                <v-icon icon="fa:fas fa-list"></v-icon>
                <v-icon icon="fa:fas fa-edit"></v-icon> -->


                <v-icon start icon="mdi-home"></v-icon>
                <v-icon aria-hidden="false">
                    mdi-account
                </v-icon>
                <v-spacer></v-spacer>
                
                <v-btn to="/">
                  <v-icon start icon="mdi-home"></v-icon>
                  Home
                </v-btn>

                <v-btn v-if="currentShop" to="/products">
                <!-- <v-btn to="/products"> -->
                  <v-icon start icon="mdi-factory"></v-icon>
                  Products in " {{ currentShop.name }} "
                </v-btn>
                <template v-slot:append>
                    <v-btn icon="mdi-dots-vertical"></v-btn>
                </template>
        </v-app-bar>
</template>

<style scoped>
</style>
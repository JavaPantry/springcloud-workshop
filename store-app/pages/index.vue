<script setup>
 // use shop pinia store
 import { useShopStore } from '@/stores/shop'

 const shop = useShopStore()
 const {count} = useShopStore() // decompose count from shop store
 const {shops} = useShopStore() // decompose shops from shop store

</script>
<template>
    <NuxtLayout>
        <div>
            <h2>Home</h2>
            <h5>Counter: {{count}}</h5>
            <ul>
                <li v-for="shop in shops" :key="shop.id">
                    {{shop.name}}
                </li>
            </ul>

            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. In perspiciatis omnis consequatur quod necessitatibus autem repellat aperiam quia atque fugit praesentium earum rerum minus, porro asperiores explicabo inventore animi ducimus. Itaque voluptatum facilis nobis, praesentium, quasi pariatur veniam veritatis quo eligendi, ratione libero impedit. Distinctio, delectus sapiente adipisci assumenda obcaecati aliquid exercitationem sit ad autem! Veritatis nihil accusamus recusandae esse?</p>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. In perspiciatis omnis consequatur quod necessitatibus autem repellat aperiam quia atque fugit praesentium earum rerum minus, porro asperiores explicabo inventore animi ducimus. Itaque voluptatum facilis nobis, praesentium, quasi pariatur veniam veritatis quo eligendi, ratione libero impedit. Distinctio, delectus sapiente adipisci assumenda obcaecati aliquid exercitationem sit ad autem! Veritatis nihil accusamus recusandae esse?</p>
        </div>
    </NuxtLayout>
</template>

<style scoped>
h2 {
    margin-bottom: 20px;
    font-size: 36px;
}
p {
    margin: 20px 0;
}
</style>
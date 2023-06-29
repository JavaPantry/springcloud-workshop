<template>
    <NuxtLayout>
        <div class="container">
            <h2>Products</h2>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. In perspiciatis omnis consequatur quod necessitatibus autem repellat aperiam quia atque fugit praesentium earum rerum minus, porro asperiores explicabo inventore animi ducimus. Itaque voluptatum facilis nobis, praesentium, quasi pariatur veniam veritatis quo eligendi, ratione libero impedit. Distinctio, delectus sapiente adipisci assumenda obcaecati aliquid exercitationem sit ad autem! Veritatis nihil accusamus recusandae esse?</p>
            <div class="container-fluid">
                <div class="row">
                    <ProductCard :product="product" v-for="product in products"/>
                </div>
            </div>
        </div>
    </NuxtLayout>
</template>

<script setup>
//  fetch the products
const { data: products } = await useFetch('https://fakestoreapi.com/products')
</script>

<style scoped>
</style>
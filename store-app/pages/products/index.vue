<template>
    <NuxtLayout>
        <div>
            <h2>Products</h2>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. In perspiciatis omnis consequatur quod necessitatibus autem repellat aperiam quia atque fugit praesentium earum rerum minus, porro asperiores explicabo inventore animi ducimus. Itaque voluptatum facilis nobis, praesentium, quasi pariatur veniam veritatis quo eligendi, ratione libero impedit. Distinctio, delectus sapiente adipisci assumenda obcaecati aliquid exercitationem sit ad autem! Veritatis nihil accusamus recusandae esse?</p>
            <div class="grid grid-cols-4 gap-5">
                <div v-for="product in products">
                    <ProductCard :product="product" />
<!--                    <h4>{{ product.title }}</h4>
                    <p>{{ product.id }}. {{ product.description }}</p>
                    <img :src="`${product.image}`"/>
                    <NuxtLink :to="`/products/${product.id}`">
                        <button class="btn btn-primary">View Details</button>
                    </NuxtLink>-->
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
h2 {
    margin-bottom: 20px;
    font-size: 36px;
    color: blue;
}
h4 {
    border-top: blue 1px solid;
    margin-bottom: 20px;
    font-size: 36px;
    color: blue;
}
p {
    margin: 20px 0;
}
img {
    max-width: 200px;
}
</style>
<template>
    <NuxtLayout>
        <div>
            <h2>Products</h2>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. In perspiciatis omnis consequatur quod necessitatibus autem repellat aperiam quia atque fugit praesentium earum rerum minus, porro asperiores explicabo inventore animi ducimus. Itaque voluptatum facilis nobis, praesentium, quasi pariatur veniam veritatis quo eligendi, ratione libero impedit. Distinctio, delectus sapiente adipisci assumenda obcaecati aliquid exercitationem sit ad autem! Veritatis nihil accusamus recusandae esse?</p>
            <div class="grid grid-cols-4 gap-5">
                <div v-for="p in products">
<!--                    <ProductCard :product="p" />-->
                    <h4>{{ p.title }}</h4>
                    <p>{{ p.description }}</p>
                    <img :src="`${p.image}`"/>
                    <button class="btn btn-primary">Detail</button>
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
<script setup>
const { product } = defineProps(['product'])
</script>

<template>
	<div class="container">
		<!--<div><ProductDetails :product="product" /></div>-->
		<div class="mybox">
			<img :src="`${product.image}`" class="thumb"/>
			<div>
				<h5>stars</h5>
			</div>
		</div>
		<div class="mybox">
			<h4>{{ product.title }}</h4>
			<p>{{ product.description }}</p>
			<p>Price : {{ product.price }}</p>
			<p>Category: {{ product.category }}</p>
			<NuxtLink :to="`/products/${product.id}`">
				<button class="btn btn-primary">View Details</button>
			</NuxtLink>
		</div>
	</div>
</template>


<style scoped>
.container {
	display: flex;
	flex-direction: row;
}

.mybox {
	display: flex;
	flex-direction: column;
	border: blue 1px solid;
	margin: 20px;
}

.thumb {
	max-height: 200px;
	max-width: 200px;
	margin: 0 auto;
}

</style>
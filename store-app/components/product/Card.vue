<script setup>
const { product } = defineProps(['product'])
</script>

<template>
	<div class="card" style="width: 18rem;">
		<img :src="`${product.image}`" class="card-img-top" :alt="`${product.title}`">
		<div class="card-body">
			<h3 class="card-title">{{ product.title }}</h3>
			<h6 class="card-subtitle">Category: {{ product.category }}</h6>
			<p class="card-text">{{ product.description }}</p>
			<NuxtLink :to="`/products/${product.id}`">
				<button class="btn btn-primary">View Details</button>
			</NuxtLink>
		</div>
	</div>
</template>


<style scoped>

</style>
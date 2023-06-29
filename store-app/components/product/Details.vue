<script setup>
const { product } = defineProps(['product'])
</script>

<template>
	<div class="card" style="width: 100%;">
		<h3 class="card-header">{{ product.title }}</h3>
		<div class="row g-0">
			<div class="col-md-2">
				<img :src="`${product.image}`" class="img-fluid rounded-start" :alt="`${product.title}`">
			</div>
			<div class="col-md-10">
				<div class="card-body">
					<h6 class="card-subtitle">Category: {{ product.category }}</h6>
					<br>
					<p class="card-text">{{ product.description }}</p>
					<NuxtLink :to="`/products/${product.id}`">
						<button class="btn btn-primary">View Details</button>
					</NuxtLink>
				</div>
			</div>
		</div>
	</div>
</template>

<style scoped>

img {
	padding: 1rem;
}
</style>
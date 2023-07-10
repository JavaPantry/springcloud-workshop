<script setup>
const { product } = defineProps(['product'])
</script>

<template>
	<!-- <div class="card" style="width: 100%;">
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
	</div> -->
	<v-card class="mx-auto">
			<v-container fluid>
		    	<v-row dense>
					<v-col cols="3">
						<v-img height="300"
						:src="`${product.image}`" class="img-fluid rounded-start" :alt="`${product.title}`"></v-img>
					</v-col>
					<v-col cols="9">
						<v-card-title class="text-h5">{{ product.title }}</v-card-title>
						<v-card-subtitle>Category: {{ product.category }}</v-card-subtitle>
						<v-card-text>{{ product.description }}</v-card-text>

						<v-card-actions>
							<v-btn to="/products" class="ml-2" variant="outlined" size="small">Back to Products</v-btn>
						</v-card-actions>
					</v-col>
				</v-row>	
			</v-container>
		</v-card>
</template>

<style scoped>

img {
	padding: 1rem;
}
</style>
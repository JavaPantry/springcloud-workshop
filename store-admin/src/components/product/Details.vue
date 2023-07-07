<script setup>
const { product } = defineProps(['product'])
console.log('ProductDetails product = ', product)
</script>

<template>
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

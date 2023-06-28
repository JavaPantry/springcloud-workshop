<script setup>
const { id } = useRoute().params
const uri = 'https://fakestoreapi.com/products/' + id

//  fetch the products
const { data: product } = await useFetch(uri, { key: id })

if (!product.value) {
	throw createError({ statusCode: 404, statusMessage: 'Product not found' })
}
</script>

<template>
	<NuxtLayout>
		<div class="container">
			<!--<div><ProductDetails :product="product" /></div>-->
			<div class="mybox">
				<h2>{{ product.title }}</h2>
			</div>
		</div>
		<div class="container">
			<!--<div><ProductDetails :product="product" /></div>-->
					<div class="mybox">
						<img :src="`${product.image}`" class="thumb"/>
						<div>
							<h5>stars</h5>
						</div>
					</div>
					<div class="mybox">
						<p>{{ product.description }}</p>
						<p>Price : {{ product.price }}</p>
						<p>Category: {{ product.category }}</p>
					</div>
<!--					<div class="mybox">
							<h4>{{ product.title }}</h4>
					</div>-->
		</div>
	</NuxtLayout>
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
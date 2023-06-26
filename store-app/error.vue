<template>
	<div>
		<div class="container">
			<h1>Global error page: This Page not found</h1>
			<p>{{error.statusCode}}</p>
			<p>{{error.message}}</p>
			<div><button @click="handleError">Go to the home page</button></div>
		</div>
	</div>
</template>


<script setup>
const props = defineProps({
	error: Object,
	required: true,
});

const handleError = () => clearError({ redirect: '/' });
</script>

<style scoped>

</style>
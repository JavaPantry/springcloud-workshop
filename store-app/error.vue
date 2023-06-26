<template>
	<div>
		<div class="container">
			<h1>Global error page: This Page not found</h1>
			<p>{{error.statusCode}}</p>
			<p>{{error.message}}</p>
      <NuxtLink to="/">Go Back home</NuxtLink>
		</div>
	</div>
</template>

<script setup>
defineProps({
	error: Object
})
</script>

<style scoped>

</style>
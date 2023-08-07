<script setup lang="ts">
import { ref } from 'vue'
import { storeToRefs}   from "pinia"
import { useShopStore } from '@/stores/shop'
import ProductInStore from 'models/ProductsInStore';

const shop = useShopStore();

// @ts-ignore
shop.fetchInventory(shop.currentShop.id) // fetch inventory for current shop

const {productsInStore} = storeToRefs(shop)

const hasInventory = computed(() => productsInStore.value.length > 0);

const confirmDeleteDialog = ref(null)
const inventoryEditForm = ref(null)

const editProduct = (product: ProductInStore) => {
    console.log('editProduct', product)
    // @ts-ignore
    inventoryEditForm.value.open(product)
}

const deleteProduct = (product: ProductInStore) => {
    console.log('deleteProduct', product)
    console.log('deleteProduct confirm reference ', confirmDeleteDialog)
    const question = "Do you really want to delete product "+ product.productId +"?";
    // @ts-ignore
    confirmDeleteDialog.value
      .open("Confirm", question)
      .then(decision => {
        if (decision) {
          deleteProductAction(product)
        }
      });
}

function deleteProductAction(product: ProductInStore) {
  console.log('Confirmed deleteProductAction ', product.productId);
}

</script>

<template>
    <NuxtLayout>
        <div class="container">
            <h2>Products in store {{ shop.currentShop.name }} id: {{ shop.currentShop.id }}</h2>
            <p> {{ shop.currentShop.description }}</p>

            <v-container fluid v-if="hasInventory">
                     <v-table density="compact">
                        <thead>
                        <tr>
                            <th class="text-left">Product Id</th>
                            <th class="text-left">Name</th>
                            <th class="text-left">Description</th>
                            <th class="text-left">Quantity</th>
                            <th class="text-left">Price</th>
                            <th class="text-left">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="item in productsInStore"
                            :key="item.id">
                            <td>{{ item.productId }}</td>
                            <td>{{ item.name }}</td>
                            <td>{{ item.description }}</td>
                            <td>{{ item.quantity }}</td>
                            <td>{{ item.price }}</td>
                            <td>
                                <v-icon aria-hidden="false" @click.stop="editProduct(item)">mdi-playlist-edit</v-icon>
                                <v-icon aria-hidden="false" color="red-darken-4" @click.stop="deleteProduct(item)">mdi-trash-can-outline</v-icon>
                            </td>
                        </tr>
                        </tbody>
                    </v-table>
            </v-container>
            <v-container fluid v-else>
                <h3>Products NOT loaded</h3><br>
            </v-container>
	        <ConfirmDialog ref="confirmDeleteDialog" />
            <InventoryEditForm ref="inventoryEditForm"/>
        </div>
    </NuxtLayout>
</template>



<style lang="scss" scoped>
.v-table__wrapper tr th {
    background-color: lightgray;
    font-size: larger;
    font-weight: 600 !important;
}
</style>
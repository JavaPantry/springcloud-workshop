<script setup>
import { ref } from 'vue'
import { storeToRefs}   from "pinia";
import { useShopStore } from '@/stores/shop'
import ConfirmDialog from "@/components/ConfirmDialog";

const shop = useShopStore();
shop.fetchInventory(shop.currentShop.id) // fetch inventory for current shop

const {inventory} = storeToRefs(shop)

const hasInventory = computed(() => inventory.value.length > 0);

const editProduct = (item) => {
    console.log('editProduct', item)
}

const confirm = ref(null)

const deleteProduct = (product) => {
    console.log('deleteProduct', product)
    console.log('deleteProduct confirm reference ', confirm)
    const id = product.product_id;
    const question = "Do you really want to delete product "+ id +"?";
    confirm.value
      .open("Confirm", question)
      .then(decision => {
        if (decision) {
          deleteProductAction(product)
        }
      });
}

function deleteProductAction(product) {
  console.log('Confirmed deleteProductAction ', product);
}

</script>

<template>
    <NuxtLayout>
        <div class="container">
            <h2>Products in store {{ shop.currentShop.name }} id: {{ shop.currentShop.id }}</h2>
            <p> {{ shop.currentShop.description }}</p>

            <v-container fluid v-if="hasInventory">
                    <!-- <ProductCard :product="product" v-for="product in products" :key="product.id"/> -->
                    <!-- <div>
                        <span>product_id | </span>
                        <span>quantity | </span>
                        <span>price</span>
                    </div>
                    <div v-for="item in inventory" :key="item.id">
                        <span>{{item.product_id}} | </span>
                        <span>{{item.quantity}} | </span>
                        <span>{{item.price}}</span>
                        <hr>
                    </div> -->

                     <v-table density="compact">
                        <thead>
                        <tr>
                            <th class="text-left">Product Id</th>
                            <th class="text-left">Quantity</th>
                            <th class="text-left">Price</th>
                            <th class="text-left">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="item in inventory"
                            :key="item.id">
                            <td>{{ item.product_id }}</td>
                            <td>{{ item.quantity }}</td>
                            <td>{{ item.price }}</td>
                            <td>
                                <v-icon aria-hidden="false" @click.stop="editProduct(item)">
                                mdi-playlist-edit
                                </v-icon>
                                <v-icon aria-hidden="false" color="red-darken-4" @click.stop="deleteProduct(item)">
                                mdi-trash-can-outline
                                </v-icon>
                                <!-- mdi-delete-outline -->
                            </td>
                        </tr>
                        </tbody>
                    </v-table>
            </v-container>
            <v-container fluid v-else>
                <h3>Products NOT loaded</h3><br>
            </v-container>
	          <ConfirmDialog ref="confirm" />
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
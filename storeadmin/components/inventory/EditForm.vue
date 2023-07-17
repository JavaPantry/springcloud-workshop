<template>
  <v-dialog v-model="show"> <!--  max-width="800"-->
    <v-card class="elevation-12">
      <v-toolbar dark color="primary">
        <v-toolbar-title><v-icon left>mdi-account</v-icon>Inventory</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-form v-model="valid" ref="form" lazy-validation>
	        <v-row>
		        <v-col cols="12" sm="4">
                <v-text-field label="Quantity" type="text" v-model="inventory.quantity"/>
            </v-col>
	          <v-col cols="12" sm="4">
              <v-text-field label="Price" type="text" v-model="inventory.price"/>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn text small @click="closeForm">Close</v-btn>
        <v-spacer/>
        <v-btn color="primary" @click="onSubmit"> <v-icon left>mdi-content-save-outline</v-icon>Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  // name: "InventoryForm",

  data () {
    return {
      valid: false,
      dialog: false,
      inventory: {}
    }
  },

  methods: {

      open(item) {
        this.inventory = item;
        this.dialog = true;
      },

      closeForm(event) {
        this.show = false;
      },

      onSubmit(event) {
        event.preventDefault()
        /*if (this.$refs.form.validate()) {
          this.saveUserProfile(this.inventory);
        }*/
        this.show = false;
      }
    },

  computed: {
    show: {
      get() {
        return this.dialog;
      },
      set(value) {
        this.dialog = value;
      }
    },
  }
}
</script>

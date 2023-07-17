/* eslint-disable vue/no-v-html */
<template>
  <v-dialog scrollable persistent v-model="show" max-width="390">
    <v-card>
      <v-toolbar dark color="primary">
        <v-toolbar-title><v-icon large left>report</v-icon>{{ title }}</v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-form v-model="valid" ref="form" lazy-validation>
        <v-row class="confirm-message-row">
          <v-col cols="12" sm="12">
            {{message}}
          </v-col>
        </v-row>
        <v-row v-if="showEmail">
          <v-text-field
              :label="$t('label.ContactEmail')"
              type="email"
              v-model="confirmEmail"
              :rules="[rules.required, rules.email]"
          ></v-text-field>
        </v-row>
          <v-row v-if="showCancelReason">
            <v-text-field
                label="Cancel Reason"
                v-model="cancelReason"
                :rules="[rules.required]"
            ></v-text-field>
          </v-row>

        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-spacer/>
        <v-btn color="primary" text @click="agree"><v-icon small left>{{ okButtonIcon }}</v-icon>{{ okButtonText }}</v-btn>
        <v-btn text  v-if="!okOnly" @click="disagree">{{ cancelButtonText }}</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "ConfirmDialog",

  data: () => ({
    dialog: false,
    resolve: null,
    reject: null,
    message: null,
    title: null,

    okOnly:false,
    okButtonText: "OK",
    okButtonIcon: 'mdi-check-bold',

    cancelButtonText: "Cancel",

    showEmail:false,
    confirmEmail: '',

    showCancelReason:false,
    cancelReason: '',

    valid: false,
    rules: {
      required: value => !!value || 'Required.',
      email: value => {
        const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        return pattern.test(value) || 'Invalid e-mail.'
      }
    }
  }),
  computed: {
    show: {
      get() {
        return this.dialog;
      },
      set(value) {
        this.dialog = value;
        if (value === false) {
          this.disagree();
        }
      }
    },
  },
  methods: {
    init(){
      this.okOnly = false;
      this.okButtonText = "OK";
      this.okButtonIcon = 'thumb_up';
      this.cancelButtonText = "Cancel";
      this.showEmail = false;
      this.showCancelReason = false;
      this.confirmEmail = '';
      this.cancelReason = '';
    },
    open(title, message) {
      this.init();
      this.dialog = true;
      this.title = title;
      this.message = message;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },

    openYesNo(title, message) {
      this.init();
      this.okButtonText = "Yes";
      this.okButtonIcon = null;
      this.cancelButtonText = "No"
      this.dialog = true;
      this.title = title;
      this.message = message;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    openOkOnly(title, message) {
      this.init();
      this.okOnly = true;
      this.okButtonText = "OK";
      this.okButtonIcon = 'mdi-check-bold';
      this.dialog = true;
      this.title = title;
      this.message = message;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    openWithEmail(title, message) {
      this.init();
      this.dialog = true;
      this.title = title;
      this.message = message;
      //this.okOnly = true;
      this.showEmail = true;

      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    openCancelConfirm(title, message) {
      this.init();
      this.dialog = true;
      this.title = title;
      this.message = message;
      //this.okOnly = true;
      this.showCancelReason=true;

      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },

    agree() {
      if(this.showEmail){
        if (!this.$refs.form.validate()) {
          console.log("ConfirmDialog form invalid")
          return;
        } else{
          // form valid  console.log("ConfirmDialog form valid, email "+ this.confirmEmail)
          this.resolve({confirm: true, confirmEmail:this.confirmEmail});
          this.dialog = false;
          return;
        }
      }

      if(this.showCancelReason){
        if (!this.$refs.form.validate()) {
          console.log("ConfirmDialog form invalid")
          return;
        } else{
          // form valid console.log("ConfirmDialog form valid, cancelReason "+ this.cancelReason)
          this.resolve({confirm: true, cancelReason:this.cancelReason});
          this.dialog = false;
          return;
        }
      }

      this.resolve(true);
      this.dialog = false;
    },
    disagree() {
      this.resolve(false);
      this.dialog = false;
    }
  }
};
</script>

<style lang="scss" scoped>
  .confirm-message-row{
    color: black;
    padding-top: 20px;
  }
</style>

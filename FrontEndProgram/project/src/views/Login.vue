<script setup>

</script>

<template>
  <div>
    <input type="text" placeholder="请输入用户名"/>
    <input type="password" placeholder="请输入密码">
    <button @click="login">登录</button>
  </div>

</template>

<script>
import axios from "axios";

var loginUrl = "http://localhost:5110/Login/UserLogin";

export default {
  data() {
    retrun
    {
      userName: "";
      password: "";
    }
    ;
  },

  methods: {
    login() {
      const params = {
        userName: this.userName,
        password: this.password,
      };
      axios.post(loginUrl, params)
          .then((resp) => {
                console.log(resp)
              }
          )
          .catch((err) => {
            console.log(err)
          });
    },
  },
};

</script>

<style scoped>

</style>

<template>
  <div id="nav">
    <router-link :to="{ name: 'home' }">Home</router-link> -
    <router-link :to="{ name: 'contact' }">Contact</router-link>
  </div>
  <router-view />
</template>

<style>
* {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}
</style>

<style scoped>
#nav {
  text-align: center;
}

a {
  text-decoration: none;
  color: black;
}

a:hover {
  text-decoration: underline;
}
</style>

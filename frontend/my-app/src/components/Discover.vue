<template>
    <div id="discoverPage">
        <h1>This is the discover page</h1>
        <a href="/postimage" class="float">
            <i class="fas fa-plus my-float"></i>
        </a>
    </div>
</template>

<script>
export default {
  name: 'Discover',
  mounted: function(){
    /*if(!localStorage.getItem('JWT'))
      window.location.href = "/";*/
    }
}
</script>


<style scoped>
.float{
	position:fixed;
	width:60px;
	height:60px;
	bottom:40px;
	right:40px;
	background-color:#155CA2;
	color:white;
	border-radius:50px;
	text-align:center;
	box-shadow: 2px 2px 3px #999;
}

.my-float{
	margin-top:22px;
}


</style>

<template>
    <div>
        This is user page
        Username: {{user.username}}
        Full Name: {{user.fullName}}
    </div>
</template>

<script>
export default {
    name: 'Profile',
    data(){
        return{
            user: ''
        }
    },
    mounted: function(){
        this.axios.get('/user', {
      headers:{
        Authorization: "Bearer " + localStorage.getItem('JWT'),
      }
    })
    .then(response => {
                        this.user = response.data;
                        console.log(response.data);
    })
    .catch(error => {
                        console.error(error);
    })
    }
}
</script>
<template>
    <div>
        <h1>This is user page</h1>
        Username: {{user.username}}<br/>
        Full Name: {{user.fullName}}<br/>
        E-mail: {{user.email}}
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
                        window.location.href = '/home'
                        console.error(error);
    })
    }
}
</script>
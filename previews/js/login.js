const app = new Vue({
    el: '#app',
    data: {
        username: '',
        password: '',
        loading: false,
        localIpAddress: ''
    },
    methods: {
        login() {
            const xhr = new XMLHttpRequest()
            const reqIp = 'http://' + this.localIpAddress + ':9090/login'
            xhr.open('POST', reqIp , false)
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        this.loading = true
                        this.$message({
                            message: '登录成功!',
                            type: 'success'
                        });
                        sessionStorage.setItem('username', this.username);
                        setTimeout(function () {
                            // 延时执行的代码
                            window.location.replace('main.html')
                        }, 1000); // 延时1秒后执行
                    } else {
                        this.$message({
                            message: jsonRes.message,
                            type: 'error'
                        })
                    }
                }
                else{
                    this.$message({
                        message: '服务异常',
                        type: 'error'
                    })
                }
            };
            xhr.send("username=" + this.username + "&password=" + this.password);
        }
    },
    created() {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', '../text/ipAddress.text', false); // 替换为你要读取的文件路径
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                this.localIpAddress = xhr.responseText
            }
        };
        xhr.send();
    }
})
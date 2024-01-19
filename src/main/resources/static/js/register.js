const app = new Vue({
    el: '#app',
    data: {
        username: '',
        password: '',
        rePassword: '',
        loading: false,
        localIpAddress: ''
    },
    methods: {
        register() {
            if (this.password !== this.rePassword) {
                this.$message({
                    message: '两次输入密码不一致',
                    type: 'error'
                })
                return
            }
            const xhr = new XMLHttpRequest()
            xhr.open('POST', 'http://' + this.localIpAddress + ':9090/register', false)
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        this.loading = true
                        this.$message({
                            message: '注册成功!',
                            type: 'success'
                        });
                        setTimeout(function () {
                            // 延时执行的代码
                            window.location.replace('login.html')
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


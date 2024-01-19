const app = new Vue({
    el: '#app',
    data: {
        //显示学生的信息
        students: [],

        //添加学生绑定的数据
        form: {},

        //模糊查询开关
        select_data: {
            value1: false
        },

        //修改用户密码
        form_data_user: {
            oldPassword: '',
            newPassword: ''
        },

        //按条件查询学生的数据
        query_data: {
            id: '',
            username: ''
        },

        //添加学生对话框的显示状态
        dialogFormVisible: false,
        //修改学生对话框的显示状态
        dialogFormVisible_update: false,
        //修改密码对话框的显示状态
        dialogFormVisible_updatePwd: false,

        formLabelWidth: '80px',

        //学生表数据中的当前行
        scopeIndex: '',

        //当前登录的用户
        loginUser: '',

        //分页查询的数据
        page: {
            currentPage: 1,
            pageNo: 1,
            pageSize: 5,
            total: 0
        },

        //本地局域网ip
        localIpAddress: ''

    },
    methods: {
        //切换pageSize时触发
        handleSizeChange(val) {
            if (val == null) {
                val = 5
            }
            this.page.pageSize = val
            this.selectWithPage()
        },
        //切换currentPage时触发
        handleCurrentChange(val) {
            this.page.currentPage = val
            this.selectWithPage()
        },

        //查询所有
        query() {
            this.students.length = 0
            const xhr = new XMLHttpRequest()
            xhr.open('Get', 'http://' + this.localIpAddress + ':9090/get', false)
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        for (let i = 0; i < jsonRes.data.length; i++) {
                            this.students.push(jsonRes.data[i]); // 将jsonRes.data添加到students数组中
                        }
                    } else {
                        this.$message({
                            message: jsonRes.message,
                            type: 'error'
                        })
                    }
                }
            };
            xhr.send();
        },

        exitlogin() {
            this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                sessionStorage.setItem('username', '')
                this.$message({
                    type: 'success',
                    message: '成功!'
                });
                window.location.replace('login.html')
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },

        addStudent() {
            const xhr = new XMLHttpRequest()

            const student = JSON.stringify(this.form)
            xhr.open('POST', 'http://' + this.localIpAddress + ':9090/add', false)
            xhr.setRequestHeader('Content-Type', 'application/json')
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        this.$message({
                            message: '添加成功!',
                            type: 'success'
                        });
                        this.selectWithPage()
                    } else {
                        this.$message({
                            message: jsonRes.message,
                            type: 'error'
                        })
                    }
                }
            };
            xhr.send(student);
        },

        deleteStudent(index) {
            this.$confirm('此操作将删除该学生, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                const sid = this.students[index].id
                const xhr = new XMLHttpRequest()
                xhr.open('DELETE', 'http://' + this.localIpAddress + ':9090/delete', false)
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                xhr.onreadystatechange = () => {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        const jsonRes = JSON.parse(xhr.responseText)
                        if (jsonRes.code === 0) {
                            this.$message({
                                message: '删除成功!',
                                type: 'success'
                            });
                            this.selectWithPage()
                        } else {
                            this.$message({
                                message: jsonRes.message,
                                type: 'error'
                            })
                        }
                    }
                };
                xhr.send("id=" + sid);

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },

        getStudentId(index) {
            const student = JSON.stringify(this.students[index])
            this.scopeIndex = JSON.parse(student).id
            this.form = this.students[index]
        },

        updateStudent() {
            const stu_old_id = this.scopeIndex

            const update_stu = JSON.stringify(this.form)
            const xhr = new XMLHttpRequest()
            xhr.open('PUT', 'http://' + this.localIpAddress + ':9090?oldId=' + stu_old_id, false)
            xhr.setRequestHeader('Content-Type', 'application/json')
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        this.$message({
                            message: '修改成功!',
                            type: 'success'
                        });
                        this.selectWithPage()
                    } else {
                        this.$message({
                            message: jsonRes.message,
                            type: 'error'
                        })
                    }
                }
            };
            xhr.send(update_stu);
        },

        updatePwd() {
            const username = sessionStorage.getItem("username");
            const oldPassword = this.form_data_user.oldPassword;
            const newPassword = this.form_data_user.newPassword;

            const xhr = new XMLHttpRequest();
            xhr.open('PATCH', 'http://' + this.localIpAddress + ':9090', false)
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        this.$message({
                            message: '修改成功!',
                            type: 'success'
                        });
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
            xhr.send("username=" + username + "&oldPassword=" + oldPassword + "&newPassword=" + newPassword);
        },

        //条件查询
        select(){
            const xhr = new XMLHttpRequest()
            const id = this.query_data.id;
            const username = this.query_data.username;
            if (id === '' && username === '') {
                this.selectWithPage()
                this.$message({
                    message: '查询成功!',
                    type: 'success'
                })
                return
            }
            xhr.open('GET', 'http://' + this.localIpAddress + ':9090/select?username=' + username + '&id=' + id, false);
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        this.$message({
                            message: '查询成功!',
                            type: 'success'
                        })
                        this.students.length = 0
                        for (let i = 0; i < jsonRes.data.length; i++) {
                            this.students.push(jsonRes.data[i])
                        }
                    } else {
                        this.$message({
                            message: jsonRes.message,
                            type: 'error'
                        })
                    }
                }
            };
            xhr.send();
        },

        //分页查询
        selectWithPage() {
            this.page.total = this.getPageTotal()
            const xhr = new XMLHttpRequest()
            xhr.open('GET', 'http://' + this.localIpAddress + ':9090/select_with_page?pageNo=' + this.page.currentPage + '&pageSize=' + this.page.pageSize, false);
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        this.students.length = 0
                        this.students = jsonRes.data
                    } else {
                        this.$message({
                            message: jsonRes.message,
                            type: 'error'
                        })
                    }
                }
            };
            xhr.send();
        },

        getPageTotal() {
            const xhr = new XMLHttpRequest()
            let totalNum = -1
            xhr.open('GET', 'http://' + this.localIpAddress + ':9090/getTotal', false)
            xhr.onreadystatechange = () => {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    const jsonRes = JSON.parse(xhr.responseText)
                    if (jsonRes.code === 0) {
                        totalNum =  jsonRes.data
                    }
                }
            };
            xhr.send()
            return totalNum
        },

    },
    // created钩子函数 实例创建完成后执行
    created() {
        //读取本地局域网ip
        const xhr = new XMLHttpRequest();
        xhr.open('GET', '../text/ipAddress.text', false); // 替换为你要读取的文件路径
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                this.localIpAddress = xhr.responseText
            }
        };
        xhr.send();

        //在分页查询前初始化pageSize和currentPage 获取pageTotal的值
        this.selectWithPage()
        loginUser = sessionStorage.getItem('username');
        if (loginUser === '' || loginUser == null) {
            this.$message({
                message: '请先登录',
                type: 'error'
            });
            setTimeout(function () {
                window.location.replace('login.html')
            }, 1500)
        } else {
            this.$message({
                message: '欢迎您' + loginUser,
                type: 'info'
            });
        }


    }
});
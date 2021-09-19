
function getUsers() {

    fetch("http://localhost:8080/rest/users")
        .then((res) => res.json())
        .then((data) => {
            let temp = "";
            data.forEach(function (user) {
                temp += `
                <tr>
                <td id="id${user.id}">${user.id}</td>
                <td id="name${user.id}">${user.name}</td> 
                <td id="surname${user.id}">${user.surname}</td>
                <td id="age${user.id}">${user.age}</td>
                <td id="email${user.id}">${user.email}</td>
                <td id="roles${user.id}">${user.roles.map(r => r.role.replace('ROLE_','')).join(', ')}</td>
                <td>
                <button class="btn btn-info btn-md" type="button"
                data-toggle="modal" data-target="#editModal" 
                onclick="fillModal(${user.id})">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#deleteModal" 
                onclick="fillModal(${user.id})">Delete</button></td>
              </tr>`;
            });
            document.getElementById("usersTable").innerHTML = temp;
        })
}
getUsers()

function fillModal(id) {
    fetch("http://localhost:8080/rest/findUser/" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('id').value = user.id;
            document.getElementById('editName').value = user.name;
            document.getElementById('editSurname').value = user.surname;
            document.getElementById('editAge').value = user.age;
            document.getElementById('editEmail').value = user.email;
            document.getElementById('editPassw').value = user.passw;

            document.getElementById('delId').value = user.id;
            document.getElementById('delName').value = user.name;
            document.getElementById('delSurname').value = user.surname;
            document.getElementById('delAge').value = user.age;
            document.getElementById('delEmail').value = user.email;
            document.getElementById('delPassw').value = user.passw;
        })
    });
}

function showUser() {
    const showUserURL = 'http://localhost:8080/rest/infoUser';
    fetch(showUserURL)
        .then((res) => res.json())
        .then((user) => {
            let temp = "<tr>";
            temp += `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${user.roles.map(r => r.role.replace('ROLE_','')).join(', ')}</td>
            `;
            temp += "<tr>";
            document.getElementById("userTable").innerHTML = temp;
        })
}
showUser();

document.getElementById("newUserForm")
    .addEventListener("submit", newUserForm);
function newUserForm(e){
    e.preventDefault();

    let name = document.getElementById("addName").value;
    let surname = document.getElementById("addSurname").value;
    let age = document.getElementById("addAge").value;
    let email = document.getElementById("addEmail").value;
    let passw = document.getElementById("addPassw").value;
    let roles = selectRole(Array.from(document.getElementById("addRole").selectedOptions)
        .map(r => r.value));

    fetch("http://localhost:8080/rest/newuser", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            surname: surname,
            age: age,
            email: email,
            passw: passw,
            roles: roles
        })
    })
        .then(() => {
            document.getElementById("usersTab").click();
            getUsers();
            document.getElementById("newUserForm").reset();
        })
}

function butEdit() {

    let user = {
        id: document.getElementById('id').value,
        name: document.getElementById('editName').value,
        surname: document.getElementById('editSurname').value,
        age: document.getElementById('editAge').value,
        email: document.getElementById('editEmail').value,
        passw: document.getElementById('editPassw').value,
        roles: selectRole(Array.from(document.getElementById("editRole").selectedOptions)
            .map(r => r.value))
    }

    fetch("http://localhost:8080/rest/update", {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)

    })
    $("#editModal .close").click();
    reloadTable();
}

function selectRole(r) {
    let roles = [];
    if (r.indexOf("USER") >= 0) {
        roles.push({"id": 1});
    }
    if (r.indexOf("ADMIN") >= 0) {
        roles.push({"id": 2});
    }
    return roles;
}

function butDelete() {
    fetch("http://localhost:8080/rest/delete/" + document.getElementById('delId').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })

    $("#deleteModal .close").click();
    reloadTable();
}

function reloadTable() {
    let table = document.getElementById('usersTable')
    if (table.rows.length > 1) {
        table.deleteRow(1);
    }
    setTimeout(getUsers, 140)
}
const urlUser = 'http://localhost:8080/api/user/'
let loggedInUser = document.querySelector('#UserInfo');
let loggedUser = document.querySelector('#navBarUser')

fetch(urlUser)
    .then(res => res.json())
    .then(data => {
        loggedUser.innerHTML = `
                <span class="align-middle mr-1">${data.name}
                with roles: ${data.roles.map(role => role.roleType === 'ROLE_ADMIN' ? ' ADMIN' : ' USER')} </span>
                `;
        loggedInUser.innerHTML = `
                                <td>${data.id}</td>
                                <td>${data.name}</td>
                                <td>${data.surname}</td>
                                <td>${data.city}</td>
                                <td>${data.username}</td>
                                <td>${data.roles.map(role => role.roleType === 'ROLE_ADMIN' ? 'ADMIN' : 'USER')}</td>
                                `;
    })
const addName = document.getElementById('nameAdd')
const addSurname = document.getElementById('surnameAdd')
const addCity = document.getElementById('cityAdd')
const addPassword = document.getElementById('passwordAdd')
const addRoles = document.getElementById('rolesAdd')
const addUsername = document.getElementById('usernameAdd')

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}
addUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: addName.value,
            surname: addSurname.value,
            city: addCity.value,
            username: addUsername.value,
            password: addPassword.value,
            roles: [
                addRoles.value
            ]
        })
    })
        .then(res => res.json())
        .then(data => {
            getAllUsers(data);
        })
    setTimeout(getAllUsers, 500);
    document.getElementById('usersTable').click()
    document.getElementById("newUserForm").reset()
});
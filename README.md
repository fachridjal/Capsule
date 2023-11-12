
# AndroidSample

This repository is a learning area for the most recent Kotlin, Jetpack, Architecture and Compose updates.

Some of the technologies included are:
- MVVM
- Clean Architecture
- LiveData
- ViewModel
- Coroutines
- Compose
- Databinding
- Hilt

---

The file structure is set up like this

```
.
├── App.kt
├── MainActivity.kt
├── data
│    ├── Result.kt
│    ├── network
│    │   └── <feature>api
│    │       ├── <Feature>Service.kt
│    │       └── response
│    │           └── <Each>Response.kt
│    └── repository
│        └── RepositoryImpl.kt
├── di
│    ├── Module.kt
│    └── qualifiers
│        └── <Each>.kt
├── domain
│    ├── <Feature>UseCase.kt
│    └── entities
│        └── Entity<Each>.kt
├── ui
│    ├── BindingAdapters.kt
│    └── <feature>
│        ├── <Feature>Fragment.kt
│        ├── <Feature>RecyclerviewAdapter.kt
│        ├── <Feature>ViewModel.kt
│        └── adapter
│            └── ViewHolder.kt
└── util
    └── Extensions.kt
```

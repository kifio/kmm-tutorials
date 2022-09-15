package me.kifio.organize

import me.kifio.organize.data.RemindersRepository
import me.kifio.organize.presentation.AboutViewModel
import me.kifio.organize.presentation.RemindersViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

object Modules {
    val repositories = module {
        factory { RemindersRepository() }
    }

    val platform = module {
        factory { Platform() }
    }

    val viewModels = module {
        factory {
            RemindersViewModel(get())
        }
        factory {
            AboutViewModel(get())
        }
    }

    fun initKoin(
        appModule: Module = module { },
        platformModules: Module = Modules.platform,
        repositoriesModule: Module = Modules.repositories,
        viewModelsModule: Module = Modules.viewModels
    ) = startKoin { modules(appModule, platformModules, repositoriesModule, viewModelsModule) }
}
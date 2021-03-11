package io.grooviter.memento.bookkeeper.userarea.application.services

import io.grooviter.memento.bookkeeper.userarea.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.userarea.application.port.out.UserAreaPorts
import io.grooviter.memento.bookkeeper.userarea.domain.vo.AccountInfo

class ShowAccountsService implements UseCases.ShowAccountsQuery {
    UserAreaPorts.ListAccountsPort listAccountsPort

    @Override
    List<AccountInfo> listAccounts() {
        return listAccountsPort.list()
    }
}

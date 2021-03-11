package io.grooviter.memento.bookkeeper.userarea.adapter.console

import de.vandermeer.asciitable.AsciiTable
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment
import io.grooviter.memento.bookkeeper.infra.console.AllCommands
import io.grooviter.memento.bookkeeper.userarea.application.port.in.UseCases
import io.grooviter.memento.bookkeeper.userarea.domain.vo.AccountInfo
import picocli.CommandLine

import javax.inject.Inject

@CommandLine.Command(
    version = '1.0',
    name = 'account-list',
    description = 'lists all accounts'
)
class ListAccountsCommand implements Runnable {
    @CommandLine.ParentCommand
    AllCommands parent

    @Inject
    UseCases.ShowAccountsQuery showAccountsQuery

    @Override
    void run() {
        List<AccountInfo> accountInfoList = showAccountsQuery.listAccounts()
        parent.printlnAnsi(renderTable(accountInfoList))
    }

    static String renderTable(List<AccountInfo> accountInfoList) {
        AsciiTable asciiTable = new AsciiTable()
        asciiTable.addRule()
        asciiTable
            .setTextAlignment(TextAlignment.CENTER)
            .addRow("ACCOUNT_ID", "HOLDER NAME", "CREATED AT")
        asciiTable.addRule()

        accountInfoList.stream().forEach {
            asciiTable.addRow(it.accountId, it.holderName, it.createdAt)
        }
        asciiTable.addRule()

        return asciiTable.render(125)
    }
}

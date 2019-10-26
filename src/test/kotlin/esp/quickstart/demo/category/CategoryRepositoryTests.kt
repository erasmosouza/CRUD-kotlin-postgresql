package esp.quickstart.demo.category

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
class CategoryRepositoryTests {

    @Autowired
    private var repository: CategoryRepository? = null

    @Autowired
    private var service: CategoryService? = null

    @Test
    fun saveTest() {
        val categoryName: String = "Minha Categoria saveTest"
        val category: Category? = repository?.save(Category(categoryName = categoryName))
        Assert.assertEquals(categoryName, category?.categoryName)
    }

    @Test
    fun updateTest() {

        val categoryName: String = "Minha Categoria updateTest"
        val newCategoryName: String = "Minha Categoria updateTest Atualizada"
        val category = repository?.save(Category(categoryName = categoryName))
        Assert.assertEquals(categoryName, category?.categoryName)

        val id: Long = category!!.id
        var createdCategory: Category? = repository?.findById(id)?.map { c -> c }?.orElse(null)
        Assert.assertNotNull(createdCategory)

        createdCategory!!.categoryName = newCategoryName
        val updatedCategory: Category? = repository?.save(createdCategory)
        Assert.assertNotNull(updatedCategory)
        Assert.assertEquals(id, updatedCategory?.id)
        Assert.assertEquals(newCategoryName, updatedCategory?.categoryName)

        val list: List<Category> = repository?.findAll()?.toList() ?: listOf()
        Assert.assertEquals(1, list.size)
    }

    @Test
    fun deleteTest(){
        val categoryNameArray = arrayOf("Primeira", "Segunda")
        for (categoryName in categoryNameArray){
            repository?.save(Category(categoryName = categoryName))
        }
        val list: List<Category> = repository?.findAll()?.toList() ?: listOf()
        Assert.assertEquals(categoryNameArray.size, list.size)

        val category = list[list.lastIndex]
        repository?.deleteById(category.id)
        val listAfterDelete: List<Category> = repository?.findAll()?.toList() ?: listOf()
        Assert.assertEquals(list.size-1, listAfterDelete.size)
    }
}